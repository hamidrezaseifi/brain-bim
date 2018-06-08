
brainApp.controller('AccountListController', function ($scope, $http, $sce, $element, $compile, NgTableParams, 
		ngTableEventsChannel, $mdSidenav, $mdDialog, $timeout, $mdComponentRegistry) {

	/*
	 *  --------------------------- scope variables --------------------------------
	 */	

	
	$scope.queryModel = {name: "", status: "", created: ""};
	
	$scope.maxRecordsList = [50, 100, 150, 200, 500, ];

	$scope.dataList = [];
	$scope.tableCustomers = false;
	$scope.tableSettings = {pagination: false, pageSize: 12, columns:{
		"sourceSystem": {title: 'Quellsystem', width:60,  sortable: 'sourceSystem', filter: {'sourceSystem': 'text'}, show: true, },
		"status": {title: 'Mandat', width:60, sortable: 'tenant', filter: {'tenant': 'text'}, show: true, },
		"created": {title: 'Mandat', width:60, sortable: 'tenant', filter: {'tenant': 'text'}, show: true, },
	} 
	};
	


	$scope.tableCustomers = createTable();
	
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

	
/*
 *  --------------------------- custome functions --------------------------------
 */	
	
	
	function doChangeTableSettings(){  
		
		var pageSize = $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.tableCustomers.total() + 1;
		var table = getCurrentTable();
		
		if(pageSize != table.count()){
			table.count(pageSize);
		}
		
		
		table.reload();
	};

	

	function createTable() {
	      var initialParams = {
	    		  page :1, count: $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.dataList.length + 1, 
	    				  sorting: { "name": "asc",  } 
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
	
	function showError(err){
		alert(err);
	}
	
});



