/**
 * Created by MBTAZ48 on 1/09/2016.
 */
(function () {

  angular
    .module('app')
    .factory('BookingService', [
      '$http',
      BookingService
    ]);

  function BookingService($http) {
    return {
      selectedFlight: null,
    }
  }

})();
