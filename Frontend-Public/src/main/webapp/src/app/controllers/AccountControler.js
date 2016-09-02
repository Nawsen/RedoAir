(function () {

  angular
    .module('app')
    .controller('AccountController', [
      '$scope',
      'NetworkService',
      '$stateParams',
      accountController
    ]);

  function accountController($scope, NetworkService, $stateParams) {
    var vm = this;


  }

})();
