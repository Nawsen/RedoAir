(function () {

    angular
        .module('app')
        .controller('FlightController', [
            '$scope',
            'NetworkService',
            'AuthenticationService',
            '$state',
            FlightController
        ]);

    function FlightController($scope, NetworkService, AuthenticationService, $state) {
        var vm = this;
        if (!AuthenticationService.isLoggedIn()) {
            $state.go('account');
        }

        NetworkService.getFlightSettings().then(function (resp) {
            vm.flights = resp.data;
            $(document).ready(function() {
                Materialize.updateTextFields();
            });
            $(document).ready(function(){
                $('.collapsible').collapsible({
                    accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
                });
            });
        });

    }

})();
