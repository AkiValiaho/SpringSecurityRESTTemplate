import {Component} from "angular2/core";
@Component({
    selector: "mapComponent",
    template: `
        <style>
        google-map {
        height: 30%;
        width: 100%;
        }
</style>
<div class="col-sm-6 col-md-6"><div class="jumbotron">
<div class="container">   <google-map latitude={{lat}} longitude={{lng}} disable-default-ui></google-map></div>
</div></div>
     
    `
})
export class MapComponent {
    lat:number = 51.678418;
    lng:number = 7.809008;
}