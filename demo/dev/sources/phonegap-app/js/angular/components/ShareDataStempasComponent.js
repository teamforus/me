module.exports = {
    templateUrl: './assets/tpl/pages/share-data-stempas.html',
    controller: [
        '$state',
        '$stateParams',
        function(
            $state,
            $stateParams
        ) {
            var ctrl = this;

            ctrl.items = $stateParams.data.requested.items;

            ctrl.respond = function(accept) {
                if (accept) {
                    window.open('stemapp://login', '_system');
                } else {
                    window.open('stemapp://login-declined', '_system');
                }
            };
        }
    ]
};