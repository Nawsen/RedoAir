(function () {

  angular
    .module('app')
    .controller('OverviewController', [
      '$scope',
      OverviewController
    ]);

  function OverviewController($scope) {
    var vm = this;
    console.log('IT WURKS BRUH');
    $scope.collapsibleElements = [{
      icon: 'mdi-image-filter-drama',
      title: 'First',
      content: 'Lorem ipsum dolor sit amet.'
    },{
      icon: 'mdi-maps-place',
      title: 'Second',
      content: 'Lorem ipsum dolor sit amet.'
    },{
      icon: 'mdi-social-whatshot',
      title: 'Third',
      content: 'Lorem ipsum dolor sit amet.'
    }
    ];

  }

})();
