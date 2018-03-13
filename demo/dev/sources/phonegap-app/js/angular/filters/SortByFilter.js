/*meApp.filter('SortBy', function() {
    return function(items, key) {
        if (!items || (typeof items.length == 'undefined'))
            return items;

        return items.sort(function(a, b) {
            var x = a[key];
            var y = b[key];
            return ((x < y) ? -1 : ((x > y) ? 1 : 0));
        });
    };
});*/