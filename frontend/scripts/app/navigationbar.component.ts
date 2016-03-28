import {Component} from "angular2/core";
import {AppComponent} from "./app.component";
import {NgClass} from "angular2/common";
@Component({
    directives: [AppComponent, NgClass],
    selector: 'nav-bar'
    , template: `
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://www.linkedin.com/in/aki-v%C3%A4liaho-19977089">Aki Väliaho</a>
        </div>
        <ul class="nav navbar-nav">
            <li [ngClass]="{active: isActiveHomeButton}"><a (click)="onClickHomeButton()" href="#">Home</a></li>
            <li [ngClass]="{active: isActiveOpenSourceButton}"><a href="j">My Open Source work</a></li>
            <li><a href="#">My contact info</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>Login to my time scheduling application</a></li>
        </ul>
    </div>
</nav>
<my-app>Loading application</my-app>
`
})
export class NavigationBarComponent {
    isActiveHomeButton = false;
    isActiveOpenSourceButton = false;

    onClickHomeButton() {
        this.isActiveHomeButton = true;
    }
}
