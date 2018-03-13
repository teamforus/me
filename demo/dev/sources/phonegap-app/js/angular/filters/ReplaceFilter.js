/*meApp.filter('ReplaceFilter', function() {
    return function(str, pattern, replacement, global){
        global = (typeof global == 'undefined' ? true : global);
        try {
            str = str ? (typeof global == 'string' ? str : str.toString()) : '';
            return str.replace(new RegExp(pattern,global ? "g": ""), function(match, group) {
                return replacement;
            });  
        } catch(e) {
            console.error("error in string.replace", e);
            return (str || '');
        }
    };
});*/