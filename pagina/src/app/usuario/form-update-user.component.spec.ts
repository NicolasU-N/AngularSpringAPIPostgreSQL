import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdateUserComponent } from './form-update-user.component';

describe('FormUpdateUserComponent', () => {
  let component: FormUpdateUserComponent;
  let fixture: ComponentFixture<FormUpdateUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormUpdateUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormUpdateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
