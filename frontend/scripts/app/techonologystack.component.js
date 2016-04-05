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
    var TechnologyStackComponent;
    return {
        setters: [
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function () {
            TechnologyStackComponent = (function () {
                function TechnologyStackComponent() {
                }

                TechnologyStackComponent.prototype.fireCustomEvent = function () {
                    if (!(this.myname === "")) {
                        this.myname = "";
                    }
                    else {
                        this.myname = "Hello World!";
                    }
                };
                TechnologyStackComponent = __decorate([
                    core_1.Component({
                        selector: 'technologies',
                        inputs: ['myname'],
                        template: "\n    <div class=\"col-sm-6 col-md-6\">\n<div class=\"jumbotron\">\n<div class=\"container\">\n        <div><h1>{{title}}</h1></div>\n<div class=\"breadtext\">\n<div class=\"col-md-12 col-sm-12 centering\"><img src=\"avatar38939_0.png\" width=\"10%\">\n<img src=\"pic_angular.jpg\" width=\"10%\">\n<img src=\"Java_logo.png\" width=\"10%\">\n<img src=\"docker.png\" width=\"10%\">\n<img src=\"nodejs.png\" width=\"10%\">\n</div>\n<div class=\"breadtext breadtext-technology\" (click)=\"fireCustomEvent()\">I'm proficient with JVM-family tools like Java EE and its framework Spring. I'm able to work with traditional SQL and modern NoSQL-based database technologies. I love to see well-structured, properly tested and easy to read -code. I'm able to code and test Node.js based code. I know Angular.js enough to get by in a full-stack business environments.\n{{myname}}\n </div>\n</div>\n</div>\n</div>\n</div>\n    "
                    }),
                    __metadata('design:paramtypes', [])
                ], TechnologyStackComponent);
                return TechnologyStackComponent;
            }());
            exports_1("TechnologyStackComponent", TechnologyStackComponent);
        }
    }
});
//# sourceMappingURL=techonologystack.component.js.map