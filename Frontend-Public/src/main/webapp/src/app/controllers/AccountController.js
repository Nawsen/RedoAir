(function () {

  angular
    .module('app')
    .controller('AccountController', [
      'NetworkService',
      accountController
    ]);

  function accountController(NetworkService) {
    var vm = this;
    vm.register = function () {
      NetworkService.postRegister(vm.user).then(function () {
        Materialize.toast('Successfully registered!', 1000);
      });
    }
  }
})();
