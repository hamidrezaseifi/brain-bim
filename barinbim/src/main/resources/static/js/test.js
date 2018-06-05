/**
 * 
 */


(function() {
  "use strict";

  mdmApp.controller("demoNgTableController", demoNgTableController);
  demoNgTableController.$inject = ["NgTableParams", "ngTableSimpleMediumList"];

  function demoNgTableController(NgTableParams, simpleList) {
    var self = this;

    self.cols = [
      { field: "name", title: "Name", sortable: "name", sortDirection: "desc" },
      { field: "age", title: "Age", sortable: "age", sortDirection: "desc" },
      { field: "money", title: "Money", sortable: false, sortDirection: "asc" }
    ];
    
    self.simpleList = [
    	{name : "A", age: 20, money: 30},
    	{name : "B", age: 20, money: 30},
    	{name : "C", age: 20, money: 30},
    	{name : "D", age: 20, money: 30},
    	{name : "E", age: 20, money: 30},
    	{name : "F", age: 20, money: 30},
    	{name : "G", age: 20, money: 30},
    	{name : "H", age: 20, money: 30},
    	{name : "I", age: 20, money: 30},
    	{name : "J", age: 20, money: 30},
    	{name : "K", age: 20, money: 30},
    	{name : "L", age: 20, money: 30},
    	{name : "M", age: 20, money: 30},
    	{name : "N", age: 20, money: 30},
    ];
    
    self.sortables = _.indexBy(self.cols, "field");
    self.tableParams = new NgTableParams({ sorting: { name: "asc"}}, {
      dataset: self.simpleList
    });
    
    self.applySelectedSort = applySelectedSort;
    
    function applySelectedSort(){
      self.tableParams.sorting(self.newSort, self.isSortDesc ? 'desc' : 'asc');
      self.newSort = "";
      self.newSortForm.$setPristine();
    }
  }
})();

(function() {
  "use strict";

  mdmApp.controller("demoNgTableDynamicController", demoNgTableDynamicController);
  demoNgTableDynamicController.$inject = ["NgTableParams", "ngTableSimpleMediumList"];

  function demoNgTableDynamicController(NgTableParams, simpleList) {
    var self = this;
    
    self.cols = [
      { field: "name", title: "Name", sortable: "name", sortDirection: "asc", show: true },
      { field: "age", title: "Age", sortable: "age", sortDirection: "desc", show: true },
      { field: "money", title: "Money", sortable: false, sortDirection: "asc", show: true }
    ];
    self.tableParams = new NgTableParams({ sorting: { age: "desc"}}, {
      dataset: simpleList
    });
    
    self.applySelectedSort = applySelectedSort;
    
    function applySelectedSort(){
      self.tableParams.sorting(self.newSort, self.isSortDesc ? 'desc' : 'asc');
      self.newSort = "";
      self.newSortForm.$setPristine();
    }
  }
})();

(function(){
  "use strict";

  mdmApp.directive("demoColumnsSorter", directive);
  
  directive.$inject = [];
    
  function directive(){
    return {
      restrict: "E",
      templateUrl: "columnsSorter.html",
      replace: true,
      scope: {
        columns: "=",
        params: "="
      },
      controller: demoColumnsSorterController,
      controllerAs: "dctrl",
      bindToController: true
    };
  }
    
  demoColumnsSorterController.$inject = ["$scope", "columnsSorterSvc"];
  function demoColumnsSorterController($scope, columnsSorterSvc){
    var self = this;
    self.applySort = columnsSorterSvc;
    $scope.$watch(function(){
      return self.params.sorting();
    }, function(newSorting){
      self.currentSorting = newSorting;
    });
  }
})();

(function(){
  "use strict";

  mdmApp.factory("columnsSorterSvc", columnsSorterSvc);
  
  columnsSorterSvc.$inject = [];
  
  function columnsSorterSvc(columns){
    return applySort;
    
    function applySort(cols, params, toggledCol){
      var newSorting = cols.filter(function(col){
        if (col === toggledCol){
          return !params.isSortBy(col.field);
        }
        return params.isSortBy(col.field);
      }).reduce(function(result, col){
        result[col.field] = col.sortDirection;
        return result;
      }, {});
      params.sorting(newSorting);
    }
  }
})();

(function() {
  "use strict";

  mdmApp.run(configureDefaults);
  configureDefaults.$inject = ["ngTableDefaults"];

  function configureDefaults(ngTableDefaults) {
    ngTableDefaults.params.count = 5;
    ngTableDefaults.settings.counts = [];
  }
})();

