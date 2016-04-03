System.register(["angular2/core"], function (exports_1, context_1) {
  "use strict";
  var __moduleName = context_1 && context_1.id;
  var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
  };
  var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
  };
  var core_1;
  var MapComponent;
  return {
    setters: [
      function (core_1_1) {
        core_1 = core_1_1;
      }],
    execute: function () {
      MapComponent = (function () {
        function MapComponent() {
          this.lat = 51.678418;
          this.lng = 7.809008;
        }

        MapComponent = __decorate([
          core_1.Component({
            selector: "mapComponent",
            template: "\n        <style>\n        google-map {\n        height: 30%;\n        width: 100%;\n        }\n</style>\n<div class=\"col-sm-6 col-md-6\"><div class=\"jumbotron\">\n<div class=\"container\">   <google-map latitude={{lat}} longitude={{lng}} disable-default-ui></google-map></div>\n</div></div>\n     \n    "
          }),
          __metadata('design:paramtypes', [])
        ], MapComponent);
        return MapComponent;
      }());
      exports_1("MapComponent", MapComponent);
    }
  }
});
//# sourceMappingURL=map.component.js.map