(function () {

    angular
        .module('app')
        .controller('AppsettingsController', [
            '$scope',
            'NetworkService',
            'AuthenticationService',
            '$state',
            AppsettingsController
        ]);

    function AppsettingsController($scope, NetworkService, AuthenticationService, $state) {
        var vm = this;
        if (!AuthenticationService.isLoggedIn()) {
            $state.go('account');
        }

        NetworkService.getAllSettings().then(function (resp) {
            vm.settings = resp.data;
            $(document).ready(function() {
                Materialize.updateTextFields();
            });
        });

        vm.save = function () {
            NetworkService.postAllSettings(vm.settings).then(function () {
                Materialize.toast('Saved settings!', 1000);
            }, function () {
                Materialize.toast('Save Failed!', 2000);
            });
        }

    }

})();
