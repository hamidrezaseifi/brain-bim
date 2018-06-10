
brainApp.controller('CompanyListController', function ($scope, $http, $sce, $element, $compile, NgTableParams, 
		ngTableEventsChannel, $mdSidenav, $mdDialog, $timeout, $mdComponentRegistry) {

	/*
	 *  --------------------------- scope variables --------------------------------
	 */	

	
	$scope.queryModel = {name: "", status: -1, created: ""};
	
	$scope.maxRecordsList = [50, 100, 150, 200, 500, ];

	$scope.dataList = [];
	$scope.companiesTable = false;
		
	tableColumns.a.writeFunction = function(record){ 
		return record["companyName"]; 
	};

	tableColumns.b.writeFunction = function(record){ 
		var res = "";
		for(var idx in record.telephoneNumbers){
			var tel = record.telephoneNumbers[idx];
			res += tel.telephoneName + ": +" + tel.countryCode + "-" + tel.cityCode + "-" + tel.subscriber + "<hr>";
		}
		
		return res; 
	};

	tableColumns.c.writeFunction = function(record){ 
		var res = "";
		for(var idx in record.emails){
			var email = record.emails[idx];
			res += email.emailName + ": " + email.emailaddress + "<hr>";
		}
		
		return res; 
	};

	tableColumns.d.writeFunction = function(record){ 
		return record["statusLabel"]; 
	};

	tableColumns.e.writeFunction = function(record){ 
		var m = moment(record["created"]);
		return m.format('DD.MM.YYYY');
	};
	
	$scope.tableSettings = {pagination: false, pageSize: 12, columns: tableColumns };
	

	//$scope.companiesTable = createTable();
	
	searchAccounts();
	
	/*
	 *  --------------------------- scope functions --------------------------------
	 */	

	
	$scope.reload = function(){
		searchCustomers();
		
	};

	$scope.test = function(ev){  
		
	};

	$scope.changeTableSettings = function(){  
		
		doChangeTableSettings();
	};

	$scope.sortColumn = function(columnSort, ev){
		
		var sortType = $scope.companiesTable.sorting()[columnSort.sortable] == "asc" ? "desc" : "asc";
		$scope.companiesTable.sorting(columnSort.sortable, sortType);
	}
	
/*
 *  --------------------------- custome functions --------------------------------
 */	
	
	
	function doChangeTableSettings(){  
		
		var pageSize = $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.companiesTable.total() + 1;
		var table = getCurrentTable();
		
		if(pageSize != table.count()){
			table.count(pageSize);
		}
		
		
		table.reload();
	};

	

	function createTable() {
	      var initialParams = {
	    		  page :1, count: $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.dataList.length + 1, 
	    				  sorting: { "companyName": "asc",  } 
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
	
	function searchAccounts(){
		
		var qModel = prepareQueryModel();
		
		$scope.dataList = [];
		$scope.companiesTable = createTable();		
		
		$scope.$parent.showloading = true;

		$http({
			method: "POST",
			url: "/basics/companies/search", 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: qModel				
		}).then(function(response){

			$scope.dataList = response.data.data;
			
			$scope.companiesTable = createTable();
			
			$scope.$parent.showloading = false;
		}, function errorCallback(response){ 
			$scope.$parent.showloading = false;		
			alert(response.data.message);
			
		});

	}
	
	function showError(err){
		alert(err);
	}
	
});



