System.register(["angular2/platform/browser", "./navigationbar.component"], function (exports_1, context_1) {
  "use strict";
  var __moduleName = context_1 && context_1.id;
  var browser_1, navigationbar_component_1;
  return {
    setters: [
      function (browser_1_1) {
        browser_1 = browser_1_1;
      },
      function (navigationbar_component_1_1) {
        navigationbar_component_1 = navigationbar_component_1_1;
      }],
    execute: function () {
      browser_1.bootstrap(navigationbar_component_1.NavigationBarComponent);
    }
  }
});
//# sourceMappingURL=main.js.map