var BaseController = function($rootScope, $scope, $q, $attrs, $state) {
    $rootScope.$state = $scope.$state = $state;
    $rootScope.$thisState = $scope.$thisState = $state.current;

    window.handleOpenURL = function(url_string) {
        setTimeout(function() {
            var url = new URL(url_string);
            var request = url.searchParams.get("request");

            if (request == 'StemApp') {
                $state.go('share-data-stempas', {
                    data: {
                        requested: {
                            items: [
                                'Stempas'
                            ]
                        }
                    }
                }, {
                    title: 'StemAPP',
                    subtitle: 'Share your voting pass',
                });
            }
        }, 0);
    };
};

module.exports = ['$rootScope', '$scope', '$q', '$attrs', '$state', BaseController];