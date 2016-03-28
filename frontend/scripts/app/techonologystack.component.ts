import {Component} from "angular2/core";
@Component({
    selector: 'technologies',
    template: `
    <div class="col-sm-6 col-md-6">
<div class="jumbotron">
<div class="container">
        <div><h1>{{title}}</h1></div>
        <h2>My name is Aki Väliaho</h2>
        <img src="prog.jpg" class="img-responsive" width="40%" height="100%">
<div class="breadtext">
I'm proficient with JVM-family related tools like Java EE and it's framework framework Spring.
</div>
</div>
</div>
</div>
    `
})
export class TechnologyStackComponent {

}