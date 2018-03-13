module.exports = {
    templateUrl: './assets/tpl/pages/qr-scanner-send.html',
    controller: [
        '$state',
        '$stateParams',
        'QrScanner',
        'IntentService',
        function(
            $state,
            $stateParams,
            QrScanner,
            IntentService,
        ) {
            var ctrl = this;

            if (!$stateParams.data) {
                return $state.go('send');
            }

            QrScanner.scan().then(function(text) {
                var data = JSON.parse(JSON.stringify($stateParams.data));

                data.address = text;

                $state.go('send-confirm', {
                    data: data
                })
            }, function(err) {
                if (typeof err != 'object') {
                    alert(err);
                } else {
                    console.log(err);
                }
            });

            ctrl.$onDestroy = function () {
                QrScanner.cancelScan(console.log);
            };
        }
    ]
};