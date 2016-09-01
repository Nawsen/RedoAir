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
    console.log('IT WURKS BRUH');


    vm.selectedDeparture = function (flight) {

    };


    function getAirports() {
      NetworkService.getAllAirports().then(function (response) {
        console.log(response.data);
        return response.data;
      });
    }
    vm.flights = getAirports();
  }

})();
