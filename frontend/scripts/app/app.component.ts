import {Component} from "angular2/core";
import {TechnologyStackComponent} from "./techonologystack.component";
import {MapComponent} from "./map.component";
@Component({
    selector: 'my-app',
    directives: [TechnologyStackComponent, MapComponent],
    template: `
<div class="row">

<div class="col-sm-6 col-md-6">
<div class="jumbotron">
<div class="container">
        <div><h1>{{title}}</h1></div>
        <h2>My name is Aki Väliaho</h2>
        <img src="prog.jpg" class="img-responsive" width="40%" height="100%">
<div class="breadtext">        I'm a backend oriented web engineer currently living in Tampere, Finland. I'm proficient in Java EE and
        Spring-framework. Authentication mechanisms are my passion as a hobbyist programmer.
        I'm willing to participiate in open source projects.
        Please, drop me a line.
        <p><a class="btn btn-linkedin" href="https://www.linkedin.com/in/aki-v%C3%A4liaho-19977089">LinkedIn</a></p>
</div>
</div>
</div>
</div>
<technologies></technologies>
<mapComponent></mapComponent>
<!--Ending-->
</div>
    `,
})
export class AppComponent {
    title = 'Who am I?';
    textToDisplay = 'Hello world';
}
