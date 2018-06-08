
brainApp.controller('CustomerListController', function ($scope, $http, $sce, $element, $compile, NgTableParams, 
		ngTableEventsChannel, $mdSidenav, $mdDialog, $timeout, $mdComponentRegistry) {

	/*
	 *  --------------------------- scope variables --------------------------------
	 */	

	$scope.searchUrl = "/customers/data/search";
	$scope.valueListUrl = "/helper/valuelist";
	
	$scope.queryModel = {sourceSystem: "all", tenant: "",sourceSystemCustomerId: "", lastName: "", firstName: "", birthDate: "",  
			maxRecords: 50, 
			//dateFrom: new Date(), dateTo: new Date(), id:-1, status:1,
			/*extraItems: [
				{ key: "LastName", type: "=", value:"Seifi"},
				{ key: "FirstName", type: "=", value:"Oliver"},
				{ key: "City", type: "=", value:"Lehrte"},
				//]	*/	
	};
	
	$scope.maxRecordsList = [50, 100, 150, 200, 500, ];
	$scope.tags = [];
	$scope.lockqueryModel = false;
	
	$scope.sourceSystems = [];
	$scope.tenants = [ ];
	
	$scope.addressTypes = [{value: "DEFAULT", label: "default address"}, {value: "MAIN", label: "main address"}, {value: "BILING", label: "billing address"}, {value: "SHIPING", label: "shipping address"}, ];
	$scope.contactTypes = [{value: "DEFAULT", label: "default contact"}, {value: "PRIVAT", label: "private contact"}, {value: "BUSINESS", label: "business contact"}, ];
	
	$scope.maxRecords = 200;
	$scope.dataList = [];
	$scope.tableCustomers = false;
	$scope.tableSettings = {pagination: false, pageSize: 12, columns:{
		"sourceSystem": {title: 'Quellsystem', width:60,  sortable: 'sourceSystem', filter: {'sourceSystem': 'text'}, show: true, },
		"tenant": {title: 'Mandat', width:60, sortable: 'tenant', filter: {'tenant': 'text'}, show: true, },
		"sourceSystemCustomerId": {title: 'SSC-ID', width:100, sortable: 'sourceSystemCustomerId', filter: {'sourceSystemCustomerId': 'text'}, show: false, },
		"titleSalutation": {title: 'Anrede', width:60, sortable: 'titleSalutation', filter: {'titleSalutation': 'text'}, show: false, },
		"salutationCode": {title: 'Anrede-Code', width:60, sortable: 'salutationCode', filter: {'salutationCode': 'text'}, show: false, },
		"title": {title: 'Titel', width:60, sortable: 'title', filter: {'title': 'text'}, show: false, },
		"lastName": {title: 'Nachname', width:100, sortable: 'lastName', filter: {'lastName': 'text'}, show: true,  },
		"firstName": {title: 'Vorname', width:100, sortable: 'firstName', filter: {'firstName': 'text'}, show: true, },
		"birthDate": {title: 'Geburtsdatum', width:100, sortable: 'birthDate', filter: {'birthDate': 'text'}, show: true, },
		"addlist": {title: 'Adresse', width:0, sortable: false, filter: false, show: true, },
		"tellist": {title: 'Telefon', width:250, sortable: false, filter: false, show: true, },
		"emaillist": {title: 'E-Mail', width:250, sortable: false, filter: false, show: true, },
	} 
	};
	
	$scope.isBirthDateValid = true;
			
	loadInitData();
	loadDialogTemplates();
	
	
	$scope.$watch(function(){
	      return $mdComponentRegistry.get('optionsSidenav') ? $mdSidenav('optionsSidenav').isOpen() : true;
	    }, 
	    function(newVal){
	    	if($mdSidenav('optionsSidenav').isOpen()){ 
	    		if(!angular.element("body").hasClass("overdialog")){
	    			angular.element("body").addClass("overdialog");
	    		}
	    		
	    	} 
	    	else{
	    		angular.element("body").removeClass("overdialog");
	    	}
	});

	$scope.tableCustomers = createTable();
	
	/*
	 *  --------------------------- scope functions --------------------------------
	 */	

	$scope.addClearFilterButton = function(){ 
		doAddClearFilterButton();
	};
	
	doAddClearFilterButton();
	
	$scope.clearFilter = function(ev, span){ 
		$(ev.currentTarget).parent().children("input").val(""); 
		$(ev.currentTarget).parent().children("input").change()
	};
	
	$scope.reload = function(){
		searchCustomers();
		
	};

	$scope.addSearchItem = function(){  

		$scope.queryModel.extraItems.push({ key: "t1", type: "<", value:"12"});
	};

	$scope.test = function(ev){  
		
	};
	
	$scope.toggleOptionsSidenav = function(){

		$mdSidenav('optionsSidenav').toggle();
    };

	$scope.closeOptionsSidenav = function () {
		
	      $mdSidenav('optionsSidenav').close();
	};

    $scope.validateBirthDate = function(){
    	doValidateBirthDate();
    }

	$scope.changeTableSettings = function(){  
		
		doChangeTableSettings();
	};
		
	$scope.editRecord = function(customer, ev, callbackAfter){
		//editCustomer(customer, ev);
		loadCustomerData(customer, ev, editCustomer, callbackAfter);
	};
	
	$scope.showRecordDetails = function(customer, ev){
		doShowCustomerDetails(customer, ev);
	
	};
	
	$scope.saveCustomer = function(customer, dialogScope){
		
		doSaveCustomer(customer, dialogScope);	
	};

	
/*
 *  --------------------------- custome functions --------------------------------
 */	
	
	function doSaveCustomer(customer, dialogScope){
		
		if(customer.birthDate instanceof Date){
			var m = moment(customer.birthDate);
			customer.birthDate = m.format('YYYY-MM-DD');
		}
		dialogScope.saveMessage = "";
		
		
		
		//$scope.$parent.showloading = true;
		var url = "/customers/data/save";
		$http({
			method: "POST",
			url: url, //+ urlQuery(1)
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: customer				
		}).then(function(response){
			
			dialogScope.lockEdit = false;
			updateRecordById(customer.id, customer);
			$mdDialog.hide();	

		}, function errorCallback(response){ 
			dialogScope.showloading = false;
			dialogScope.lockEdit = false;
			showError(response.data.message);
			dialogScope.saveMessage = response.data.message;
		});	
	}
	
	function doChangeTableSettings(){  
		
		var pageSize = $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.tableCustomers.total() + 1;
		var table = getCurrentTable();
		
		if(pageSize != table.count()){
			table.count(pageSize);
		}
		
		
		table.reload();
	};

	
	function loadCustomerData(customer, ev, editFunc, callbackAfter){
		
		var qModel = {sourceSystem: customer.sourceSystem, tenant: customer.tenant,sourceSystemCustomerId: customer.sourceSystemCustomerId, 
				lastName: "", firstName: "", birthDate: "", maxRecords: 1, 
		};
		
		
		$scope.$parent.showloading = true;
		$http({
			method: "POST",
			url: $scope.searchUrl, 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: qModel				
		}).then(function(response){
			
			$scope.$parent.showloading = false;
			
			var editRrecord = response.data.data[0];
			
			if(typeof editFunc == "function"){
				editFunc(editRrecord, ev);
			}
			
			if(typeof callbackAfter == "function"){
				callbackAfter(editRrecord);
			}
			
		}, function errorCallback(response){ 
			$scope.$parent.showloading = false;	
			showError(response.data.message);
			
		});
		
	}

	
	function editCustomer(editRrecord, ev){
		
					
		if(editRrecord != null){
			editRrecord.recordVersion = isNaN(editRrecord.recordVersion) ? 1 : Number(editRrecord.recordVersion) + 1;
			
			editRrecord.birthDate = editRrecord.birthDate != null && editRrecord.birthDate != "" ? new Date(editRrecord.birthDate) : null;
			
			editRrecord.addresses = editRrecord.addresses == undefined ? [] : editRrecord.addresses;
			editRrecord.telephoneNumbers = editRrecord.telephoneNumbers == undefined ? [] : editRrecord.telephoneNumbers;
			editRrecord.emailAddresses = editRrecord.emailAddresses == undefined ? [] : editRrecord.emailAddresses;
			
		}
		
		$mdDialog.show({
		      controller: EditRecordController,
		      templateUrl: 'edit_customer_template',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:false,
		      fullscreen: false,
		      locals:{record: editRrecord, addressTypes: $scope.addressTypes
		    	  , contactTypes: $scope.contactTypes, parentScope: $scope, },
		})
		.then(function(answer) {
		      
		}, function() {
		      
		});
					
		
	}	
	function doShowCustomerDetails(customer, ev){
		

		var editRrecord = angular.copy(customer);
		
		if(editRrecord != null){
			editRrecord.recordVersion = isNaN(editRrecord.recordVersion) ? 1 : Number(editRrecord.recordVersion) + 1;
			
			editRrecord.birthDate = editRrecord.birthDate != null && editRrecord.birthDate != "" ? new Date(editRrecord.birthDate) : null;
			
			editRrecord.addresses = editRrecord.addresses == undefined ? [] : editRrecord.addresses;
			editRrecord.telephoneNumbers = editRrecord.telephoneNumbers == undefined ? [] : editRrecord.telephoneNumbers;
			editRrecord.emailAddresses = editRrecord.emailAddresses == undefined ? [] : editRrecord.emailAddresses;
			
		}
		
		$mdDialog.show({
		      controller: ShowRecordController,
		      templateUrl: 'view_customer_template',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:false,
		      fullscreen: false,
		      locals:{record: editRrecord, addressTypes: $scope.addressTypes
		    	  , contactTypes: $scope.contactTypes, parentScope: $scope, showEditButton: true, },
		})
		.then(function(answer) {
		      
		}, function() {
		      
		});

		
	}

	function doValidateBirthDate(){
    	var dateString = angular.element('md-datepicker.searchBDate div.md-datepicker-input-container input').val();
    	if(dateString == "" || dateString == null){
    		$scope.isBirthDateValid = true;
    		return;
    	}
    	var m = moment(dateString, 'D.M.YYYY', true);
    	$scope.isBirthDateValid = m.isValid() || dateString == "" || dateString == null;

    }
	
	function doAddClearFilterButton(){ 
		$timeout(function() {
			
			angular.element("span.clear-filter").remove();
			
			var clearbtn = angular.element('<span class="clear-filter" ng-click="clearFilter($event, this)" ><i class="material-icons">clear</i></span>');
			angular.element(".ng-table th.filter .input-filter").after(clearbtn);
			
			clearbtn = angular.element(".ng-table th.filter .clear-filter");
			
			$compile(clearbtn)($scope);
			
			
	    }, 500);
	};
	
	function loadInitData(){
		
		$scope.$parent.showloading = true;
		
		$http({
			method: "GET",
			url: $scope.valueListUrl, 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: $scope.queryModel				
		}).then(function(response){
			
			$scope.$parent.showloading = false;

			$scope.sourceSystems = response.data.data.sourceSystems;
			$scope.tenants = response.data.data.tenants;
			if($scope.tenants.length && $scope.tenants.length > 0){
				$scope.queryModel.tenant = $scope.tenants[0];
			}
			if(response.data.data.addressTypes){
				$scope.addressTypes = response.data.data.addressTypes;
			}
			if(response.data.data.contactTypes){
				$scope.contactTypes = response.data.data.contactTypes;
			}
			
			$scope.$parent.showloading = false;
		}, function errorCallback(response){ 
			$scope.$parent.showloading = false;	
			showError(response.data.message);
			
		});
	}
		
	function loadDialogTemplates(){
		
		//$scope.$parent.showloading = true;
		
		var scripts = angular.element("script[type='text/ng-template']");
		
		for(var i=0; i<scripts.length; i++){
			
			var sc = scripts[i];

			loadSingleTemplate(sc);
			
		}
		
	}
	
	function loadSingleTemplate(script){
		$scope.$parent.showloading = true;
		
		var url = "/helper/dialog/" + script.id;
		$http({
			method: "GET",
			url: url, 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: $scope.queryModel				
		}).then(function(response){
			
			script.text = response.data;
			$compile(angular.element("#" + script.id))($scope);
			
			$scope.$parent.showloading = false;
		}, function errorCallback(response){ 
			$scope.$parent.showloading = false;	
			for(o in response.data){
				//alert(o);
			}
			//showError(response.data.path);
			//showError(response.data.error);
			//showError(response.data.message);
			var msg = dialogError;
			msg = msg.replace("%1" , "\r\n" + url + "\r\n");
			showError(msg);
		});	
	}
	
	function getCurrentTable(){
		return $scope.tableCustomers;
		
	}

	function createTable() {
	      var initialParams = {
	    		  page :1, count: $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.dataList.length + 1, 
	    				  sorting: { "lastName": "asc",  } 
	      };
	      var initialSettings = {
	    		  total: $scope.dataList.length,
	        counts: [],

	        paginationMaxBlocks: 10,
	        paginationMinBlocks: 2,
	        dataset: $scope.dataList,
	        
	      };
	      
	      return new NgTableParams(initialParams, initialSettings);
	}
	
	function prepareQueryModel(){
		var qModel = angular.copy($scope.queryModel);
		if(qModel.birthDate instanceof Date){
			var m = moment(qModel.birthDate);
			qModel.birthDate = m.format('YYYY-MM-DD');
		}
		
		return qModel;
	}
	
	function searchCustomers(){
		
		if(!$scope.isBirthDateValid){
			return;
		}
		
		var qModel = prepareQueryModel();
		
		qModel.sourceSystem = qModel.sourceSystem == "all" ? "" : qModel.sourceSystem;
		
		$scope.dataList = [];
		$scope.tableCustomers = createTable();		
		
		$scope.$parent.showloading = true;

		$http({
			method: "POST",
			url: $scope.searchUrl, 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: qModel				
		}).then(function(response){
			
			$scope.dataList = response.data.data;
			
			$scope.tableCustomers = createTable();
			
			$scope.$parent.showloading = false;
		}, function errorCallback(response){ 
			$scope.$parent.showloading = false;		
			alert(response.data.message);
			
		});

	}
	
	function getDatarecordById(id){
		
		for(var i=0; i<$scope.dataList.length; i++ ){
			if($scope.dataList[i].id == id) return $scope.dataList[i];
		}

		return null;
	}
	
	function updateRecordById(id, record){
		
		for(var i=0; i<$scope.dataList.length; i++ ){
			if($scope.dataList[i].id == id) {

				$scope.dataList[i] = record;
				$scope.tableCustomers.reload();

			}
		}
		
	}
	
	function showError(err){
		alert(err);
	}

	function EditRecordController($scope, record, addressTypes, contactTypes, parentScope) {
		
		$scope.record = record;
		$scope.addressTypes = addressTypes;
		$scope.contactTypes = contactTypes;
		$scope.isBirthDateValid = true;
		$scope.isAllDataValid = true;
		$scope.lockEdit = false;
				
		$scope.record.recordVersion = isNaN($scope.record.recordVersion) ? 1 : Number($scope.record.recordVersion) + 1;
		
		$scope.record.birthDate = $scope.record.birthDate != null && $scope.record.birthDate != "" ? new Date($scope.record.birthDate) : null;
		
		$scope.record.emailAddresses = $scope.record.emailAddresses == undefined ? [] : $scope.record.emailAddresses;
		$scope.record.addresses = $scope.record.addresses == undefined ? [] : $scope.record.addresses;
		$scope.record.telephoneNumbers = $scope.record.telephoneNumbers == undefined ? [] : $scope.record.telephoneNumbers;
			
		$scope.saveMessage = "";
		
	    $scope.test = function() {
	    	$scope.lockEdit = !$scope.lockEdit;
		};

	    $scope.hide = function() {
		      $mdDialog.hide();
		};

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.save = function() {

	    	doValidateAllEdit();
	    	$scope.lockEdit = true;
	    	if($scope.record.id != undefined && $scope.isAllDataValid){
	    		parentScope.saveCustomer($scope.record, $scope);
	    	}      
	    };
	    
	    $scope.validateAll = function(){
	    	doValidateAllEdit();
	    }
	    
	    $scope.addNewEmail = function(ev) {
	    	
	    	$scope.record.emailAddresses = $scope.record.emailAddresses.concat( { contactType: $scope.contactTypes[0], emailAddress: ""});
	    };
	    
	    $scope.deleteEmail = function(ev, email, index) {
	    	$scope.record.emailAddresses.splice(index, 1); 
	    };
	    
	    $scope.addNewTel = function(ev) {
	    	$scope.record.telephoneNumbers = $scope.record.telephoneNumbers.concat( { contactType: $scope.contactTypes[0], countryPart: "", 
				areaPart: "", subscriberPart: "", });
	    };
	    
	    $scope.deleteTel = function(ev, email, index) {
	    	$scope.record.telephoneNumbers.splice(index, 1); 
	    };
	    
	    $scope.addNewAddress = function(ev) {
	    	$scope.record.addresses = $scope.record.addresses.concat( {addressType: $scope.addressTypes[0], countryCode: "D",
				street: "", houseNumber: "", zipCode: "", city: "", });
	    };
	    
	    $scope.deleteAdress = function(ev, email, index) {
	    	$scope.record.addresses.splice(index, 1); 
	    };
	    
		function doValidateBirthDateEdit(){
	    	var dateString = angular.element('md-datepicker.birthdate div.md-datepicker-input-container input').val();
	    	if(dateString == null || dateString == ""){
	    		$scope.isBirthDateValid = true;
	    	}
	    	else{
	    		var m = moment(dateString, 'D.M.YYYY', true);
		    	$scope.isBirthDateValid = m.isValid();	
	    	}
	    	

	    }

		function doValidateAllEdit(){
			doValidateBirthDateEdit();
			$scope.isAllDataValid = $scope.isBirthDateValid;
			for(addr in $scope.record.addresses){
				$scope.isAllDataValid &= $scope.record.addresses[addr].addressType != "";
			}
			for(addr in $scope.record.telephoneNumbers){
				$scope.isAllDataValid &= $scope.record.telephoneNumbers[addr].contactType != "";
			}
			for(addr in $scope.record.emailAddresses){
				$scope.isAllDataValid &= $scope.record.emailAddresses[addr].contactType != "";
			}
	    }
	}
	
	function ShowRecordController($scope, record, addressTypes, contactTypes, parentScope, showEditButton) {
		
		$scope.record = record;
		$scope.addressTypes = addressTypes;
		$scope.contactTypes = contactTypes;
		$scope.isBirthDateValid = true;
		$scope.showEditButton = showEditButton;
		
		$scope.record.recordVersion = isNaN($scope.record.recordVersion) ? 1 : Number($scope.record.recordVersion) + 1;
		
		$scope.record.birthDate = $scope.record.birthDate != null && $scope.record.birthDate != "" ? new Date($scope.record.birthDate) : null;
		
		$scope.record.emailAddresses = $scope.record.emailAddresses == undefined ? [] : $scope.record.emailAddresses;
		
		$scope.saveMessage = "";
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	    	$mdDialog.cancel();
		};

		$scope.edit = function(ev) {
			
			$scope.isLoading = true;
			
			parentScope.editRecord($scope.record, ev, function(){ $mdDialog.cancel(); });
		};
	}	
	
	function TestDialogController($scope, parentScope) {
		
		$scope.testlist = ["Item 1", "Item 2", "Item 3", "Item 4", ];
							
		$scope.saveMessage = "";
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	    	$mdDialog.cancel();
		};

	    $scope.edit = function(ev) {
			$mdDialog.cancel();
			
			
		};
	}	
});





