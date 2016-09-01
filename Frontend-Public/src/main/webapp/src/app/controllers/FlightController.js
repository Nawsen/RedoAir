(function () {

  angular
    .module('app')
    .controller('FlightController', [
      '$scope',
      'NetworkService',
      FlightController
    ]);

  function FlightController($scope, NetworkService) {
    var vm = this;


    function getFlight() {
      NetworkService.getFlights("FLA-542","OST-002").then(function (response) {
        vm.flights = response.data;
        console.log(response.data);
      });
    }
    vm.flights = getFlight();
  }

})();
