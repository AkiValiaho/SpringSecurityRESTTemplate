import {Component} from "angular2/core";
import {AppComponent} from "./app.component";
import {NgClass} from "angular2/common";
import {OpenSourceComponent} from "./opensource.component";

@Component({
    directives: [AppComponent, NgClass, OpenSourceComponent],
    selector: 'nav-bar'
    , template: `
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://www.linkedin.com/in/aki-v%C3%A4liaho-19977089">Aki Väliaho</a>
        </div>
        <ul class="nav navbar-nav">
            <li [ngClass]="{active: isActiveHomeButton}"><a (click)="onClickHomeButton()" href="#">Home</a></li>
            <li [ngClass]="{active: isActiveOpenSourceButton}"><a (click)="onClickOpenSourceButton()" href="#">My Open Source work</a></li>
            <li [ngClass]="{active: isActiveContactButton}"><a (click)="onClickContactInfoButton()" href="#">My contact info</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>Login to my time scheduling application</a></li>
        </ul>
    </div>
</nav>
<!--Display the application if home button is active-->
<my-app *ngIf="isActiveHomeButton">Loading application</my-app>
<openSourceApp item1="Herld" item2="Derp" *ngIf="isActiveOpenSourceButton"></openSourceApp>
`
})
/*Navigation bar component and */
export class NavigationBarComponent {
    isActiveHomeButton = true;
    isActiveOpenSourceButton = false;
    isActiveContactButton = false;

    onClickHomeButton() {
        this.isActiveOpenSourceButton = false;
        this.isActiveContactButton = false;
        this.isActiveHomeButton = true;
    }

    onClickOpenSourceButton() {
        this.isActiveHomeButton = false;
        this.isActiveContactButton = false;
        this.isActiveOpenSourceButton = true;
    }

    onClickContactInfoButton() {
        this.isActiveHomeButton = false;
        this.isActiveOpenSourceButton = false;
        this.isActiveContactButton = true;
    }
}
