import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAccountPageComponent } from './customer-account-page.component';

describe('CustomerAccountPageComponent', () => {
  let component: CustomerAccountPageComponent;
  let fixture: ComponentFixture<CustomerAccountPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAccountPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAccountPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
