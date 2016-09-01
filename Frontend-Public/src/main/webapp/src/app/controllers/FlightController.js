(function () {

  angular
    .module('app')
    .controller('FlightController', [
      '$scope',
      'NetworkService',
      '$stateParams',
      FlightController
    ]);

  function FlightController($scope, NetworkService, $stateParams) {
    var vm = this;


    function getFlight() {
      NetworkService.getFlights($stateParams.departure,$stateParams.arrival).then(function (response) {
        vm.flights = response.data;
        console.log(response.data);
      });
    }
    vm.flights = getFlight();
  }

})();
