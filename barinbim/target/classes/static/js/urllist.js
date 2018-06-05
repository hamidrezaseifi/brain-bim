var urlmdmCore = "";

mdmApp.controller('UrlListController', function ($scope, $http, $sce, $element, $compile, NgTableParams, ngTableEventsChannel, $mdSidenav, $mdDialog) {

	
	$scope.urlList = {mdmCore : "" , };
	
	reloadAll();
	
	$scope.saveMdmCoreUrl = function(ev){
		
		$scope.$parent.showloading = true;
		
		var item = new Object();
		item.name = "mdmcore";
		item.value = $scope.urlList.mdmCore;
		
		$http({
			method: 'POST',
			url: "/settings/url/set",
			data: item,
			headers: {
				   'Content-Type': 'application/json'
				 },			
		}).then(function(response){
			
			$scope.$parent.showloading = false;
			
			if(response.data.status == "ok"){
				alert("Daten erfolgreich gespeichert.");
			}
			
		});
	};
	
	$scope.revertMdmUrl = function(ev){
		reloadAll();
	};
	
	
	function reloadAll(){
		
		$scope.$parent.showloading = true;
		
		$http({
			method: 'GET',
			url: "/settings/url/getall"
		}).then(function(response){
			
			$scope.$parent.showloading = false;
			
			if(response.data.status == "ok"){
				$scope.urlList.mdmCore = response.data.data.mdmcore;
			}
			
		});

	}
});





