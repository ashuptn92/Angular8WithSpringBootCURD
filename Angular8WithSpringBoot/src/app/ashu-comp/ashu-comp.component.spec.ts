import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AshuCompComponent } from './ashu-comp.component';

describe('AshuCompComponent', () => {
  let component: AshuCompComponent;
  let fixture: ComponentFixture<AshuCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AshuCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AshuCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
