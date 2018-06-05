

mdmApp.controller('PersonsMergController', ['$scope', '$http', '$sce', '$element', '$compile', function ($scope, $http, $sce, $element, $compile) {

	$scope.$parent.showloading = true;
	
	$scope.searchData = {sourceSystemID:1, mandatorID:1, maxRecords: 100, dateFrom: new Date(), dateTo: new Date(), id:-1, status:2,
			extraItems: [
				/*{ key: "LastName", type: "=", value:"Seifi"},
				{ key: "FirstName", type: "=", value:"Oliver"},
				{ key: "City", type: "=", value:"Lehrte"},*/
				]		
	};
	
	$scope.tableSettings = {pagination: true, pageSize: 12, columns:{
		"personal": {title: 'Persönliche Daten', sortable: 'golden.lastName', filter: {'golden.lastName': 'text', 'golden.firstName': 'text'}, show: true, },
		"lname": {title: 'Nachname', sortable: 'golden.lastName', filter: {'golden.lastName': 'text', 'golden.firstName': 'text'}, show: false, },
		"fname": {title: 'Vorname', sortable: 'golden.lastName', filter: {'golden.lastName': 'text', 'golden.firstName': 'text'}, show: false, },
		"bdate": {title: 'Geburtsdatum', sortable: 'golden.lastName', filter: {'golden.lastName': 'text', 'golden.firstName': 'text'}, show: false, },
		"addlist": {title: 'Adresse', sortable: 'golden.addresses[0].city', filter: false, show: true, },
		"tellist": {title: 'Telefone', sortable: 'golden.telephoneNumbers[0].areaPart', filter: false, show: true, },
		"emaillist": {title: 'E-Mail', sortable: 'golden.emailAddresses[0].emailAddress', filter: false, show: true, },
	} };
	
	$scope.showdetails = false;
	$scope.merginglist = [];
	$scope.currentCluster = false;
	$scope.currentClusterIndex = -1;
	$scope.currentClusterID = -1;
	$scope.personslist = [];
	
	$scope.reloadClusters = loadClusters;
	
	loadClusters();
	
	$scope.removeSource = function(id, index){
		
		$scope.merginglist[$scope.currentClusterIndex].sourceRecords[index].deleted = true;
		showMergDetail($scope.currentClusterID, $scope.currentClusterIndex);
	};

	$scope.showMergDetail = function(id, index){
		
		$scope.currentClusterIndex = index;
		$scope.currentClusterID = id;
		if(id == -1 || index == -1) return;
		
		var cluster = $scope.merginglist[index];
		
		for(var i=0; i< cluster.sourceRecords.length; i++){
			cluster.sourceRecords[i].deleted = false;
		}
		$scope.currentCluster = cluster;
		
		return;
		$scope.showdetails = true;
		$scope.personslist = [];
		$scope.$parent.showloading = true;
		
		$http({
			method: 'GET',
			url: "/persons/data/all?mid=1&ssid=1&cnt=5"
		}).then(function(response){
			
			if(response.data.status == "ok"){
				$scope.personslist = response.data.data;
			}
			
			$scope.$parent.showloading = false;
			
		});
		
	};


	function loadClusters(){

		$scope.merginglist = [];

		
		$scope.$parent.showloading = true;
		var url = "/persons/data/cluster/all";
		$http({
			method: "POST",
			url: url, //+ urlQuery(1)
			headers: {
				   "Content-Type": "application/json"
				 },
		    data: $scope.searchData				
		}).then(function(response){
			
			if(response.data.status == "ok"){
				$scope.merginglist = response.data.data; 
			}
			else{
				alert(response.data);
			}
			
			$scope.$parent.showloading = false;
		});

	}	
}]);




