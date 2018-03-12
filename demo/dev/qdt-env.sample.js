module.exports = {
    "server": {
        "enabled": true,
        "platform": [
            "client-front-kindpakket", 
            "phonegap-html"
        ],
        "watch_platforms": "all"
    },
    "platforms": {
        "enabled": [
            "client-front-kindpakket",
            "phonegap-html",
            "phonegap"
        ]
    },
    "platforms_data": {
        "client-front-kindpakket": {
            "html5Mode": {
                "enable": false,
                "basePath": "/"
            },
            "apiUrl": "http://kindpakket-api.dev-rminds.nl/api/",
            "credentials_key": "zuidhorn-front-kindpakket-credentials",
            "winkelierUrl": "https://test.winkelier.forus.io/",
            "kindpakketUrl": "https://test.zuidhorn.forus.io/kindpakket/"
        }
    }
};
