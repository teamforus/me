module.exports = {
    templateUrl: './assets/tpl/pages/wallet-passes.html',
    controller: [
        '$state',
        'AuthService',
        function(
            $state,
            AuthService
        ) {
            var ctrl = this;

            AuthService.records().then(function(res) {
                ctrl.records = res.data;
            }, function(res) {
                console.log(res.data);
            });

            /*AuthService.tokens().then(function(res) {
                console.log(res.data);
            }, function(res) {
                console.log(res.data);
            });*/
        }
    ]
};