(function () {

  angular
    .module('app')
    .controller('OrderController', [
      '$scope',
      'NetworkService',
      OrderController
    ]);

  function OrderController($scope, NetworkService) {
    var vm = this;

  }

})();
