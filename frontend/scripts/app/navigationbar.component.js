System.register(["angular2/core", "./app.component", "angular2/common"], function (exports_1, context_1) {
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
  var core_1, app_component_1, common_1;
  var NavigationBarComponent;
  return {
    setters: [
      function (core_1_1) {
        core_1 = core_1_1;
      },
      function (app_component_1_1) {
        app_component_1 = app_component_1_1;
      },
      function (common_1_1) {
        common_1 = common_1_1;
      }],
    execute: function () {
      NavigationBarComponent = (function () {
        function NavigationBarComponent() {
          this.isActiveHomeButton = true;
          this.isActiveOpenSourceButton = false;
          this.isActiveContactButton = false;
        }

        NavigationBarComponent.prototype.onClickHomeButton = function () {
          this.isActiveOpenSourceButton = false;
          this.isActiveContactButton = false;
          this.isActiveHomeButton = true;
        };
        NavigationBarComponent.prototype.onClickOpenSourceButton = function () {
          this.isActiveHomeButton = false;
          this.isActiveContactButton = false;
          this.isActiveOpenSourceButton = true;
        };
        NavigationBarComponent.prototype.onClickContactInfoButton = function () {
          this.isActiveHomeButton = false;
          this.isActiveOpenSourceButton = false;
          this.isActiveContactButton = true;
        };
        NavigationBarComponent = __decorate([
          core_1.Component({
            directives: [app_component_1.AppComponent, common_1.NgClass],
            selector: 'nav-bar',
            template: "\n    <nav class=\"navbar navbar-inverse\">\n    <div class=\"container-fluid\">\n        <div class=\"navbar-header\">\n            <a class=\"navbar-brand\" href=\"https://www.linkedin.com/in/aki-v%C3%A4liaho-19977089\">Aki V\u00E4liaho</a>\n        </div>\n        <ul class=\"nav navbar-nav\">\n            <li [ngClass]=\"{active: isActiveHomeButton}\"><a (click)=\"onClickHomeButton()\" href=\"#\">Home</a></li>\n            <li [ngClass]=\"{active: isActiveOpenSourceButton}\"><a (click)=\"onClickOpenSourceButton()\" href=\"#\">My Open Source work</a></li>\n            <li [ngClass]=\"{active: isActiveContactButton}\"><a (click)=\"onClickContactInfoButton()\" href=\"#\">My contact info</a></li>\n        </ul>\n        <ul class=\"nav navbar-nav navbar-right\">\n            <li><a href=\"#\"><span class=\"glyphicon glyphicon-log-in\"></span>Login to my time scheduling application</a></li>\n        </ul>\n    </div>\n</nav>\n<!--Display the application if home button is active-->\n<my-app *ngIf=\"isActiveHomeButton\">Loading application</my-app>\n"
          }),
          __metadata('design:paramtypes', [])
        ], NavigationBarComponent);
        return NavigationBarComponent;
      }());
      exports_1("NavigationBarComponent", NavigationBarComponent);
    }
  }
});
//# sourceMappingURL=navigationbar.component.js.map