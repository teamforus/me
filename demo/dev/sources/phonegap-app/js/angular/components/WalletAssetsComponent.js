module.exports = {
    templateUrl: './assets/tpl/pages/wallet-assets.html',
    controller: [
        '$state',
        'AuthService',
        function(
            $state,
            AuthService
        ) {
            var ctrl = this;

            AuthService.records().then(function(res) {
                console.log(res.data);
            }, function(res) {
                console.log(res.data);
            });
        }
    ]
};