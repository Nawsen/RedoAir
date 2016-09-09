(function () {

  angular
    .module('app')
    .controller('OverviewController', [
      '$scope',
      'NetworkService',
      OverviewController
    ]);

  function OverviewController($scope, NetworkService) {
    var vm = this;

    vm.selectedDeparture;
    vm.selectedArrival;

    vm.arrivalAirports = [];
    vm.departureAirports = [];
    getAirports();


    vm.setSelectedDeparture = function (flight) {
      vm.selectedDeparture = flight;
    };
    vm.setSelectedArrival = function (flight) {
      vm.selectedArrival = flight;
    };

    vm.test = function () {
      // console.log('scrollfire works');
    };

    function getAirports() {
      NetworkService.getAllAirports().then(function (response) {
        vm.arrivalAirports = response.data;
        vm.departureAirports = response.data;
      });
    }

    vm.filterDeparture = function (filter) {
      NetworkService.getFilteredAirports(filter).then(function (response) {
        vm.departureAirports = response.data;
      })
    };
    vm.filterArrival = function (filter) {
      NetworkService.getFilteredAirports(filter).then(function (response) {
        vm.arrivalAirports = response.data;
      })
    }





  }

})();
