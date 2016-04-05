import {Component, Input} from "angular2/core";
@Component({
    selector: 'openSourceApp',
    template: `
        <div>
        <paper-menu class="menu-content">
            <paper-item>{{item1}}</paper-item>
            <paper-item>{{item2}}</paper-item>
        </paper-menu>
        </div>
    `
})
export class OpenSourceComponent {
    @Input()
    item1:string;
    @Input()
    item2:string;


    constructor() {
        this.item1 = "Hello world";
        this.item2 = "Whaddaup";
    }
}