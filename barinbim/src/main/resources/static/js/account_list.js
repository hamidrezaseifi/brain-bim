
brainApp.controller('AccountListController', function ($scope, $http, $sce, $element, $compile, NgTableParams, 
		ngTableEventsChannel, $mdSidenav, $mdDialog, $timeout, $mdComponentRegistry) {

	/*
	 *  --------------------------- scope variables --------------------------------
	 */	

	
	$scope.queryModel = {name: "", status: -1, created: ""};
	
	$scope.maxRecordsList = [50, 100, 150, 200, 500, ];

	$scope.dataList = [];
	$scope.accountsTable = false;
		
	tableColumns.accountName.writeFunction = function(record){ 
		return record["accountName"]; 
	};

	tableColumns.statusLabel.writeFunction = function(record){ 
		return record["statusLabel"]; 
	};

	tableColumns.created.writeFunction = function(record){ 
		var m = moment(record["created"]);
		return m.format('DD.MM.YYYY');
	};
	
	$scope.tableSettings = {pagination: false, pageSize: 12, columns: tableColumns };
	

	//$scope.accountsTable = createTable();
	
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

	$scope.sortColumn = function(tblColumn, ev){
		
		
	}
	
/*
 *  --------------------------- custome functions --------------------------------
 */	
	
	
	function doChangeTableSettings(){  
		
		var pageSize = $scope.tableSettings.pagination ? $scope.tableSettings.pageSize : $scope.accountsTable.total() + 1;
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
	
	function searchAccounts(){
		
		var qModel = prepareQueryModel();
		
		$scope.dataList = [];
		$scope.accountsTable = createTable();		
		
		$scope.$parent.showloading = true;

		$http({
			method: "POST",
			url: "/basics/accounts/search", 
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: qModel				
		}).then(function(response){

			$scope.dataList = response.data.data;
			
			$scope.accountsTable = createTable();
			
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



