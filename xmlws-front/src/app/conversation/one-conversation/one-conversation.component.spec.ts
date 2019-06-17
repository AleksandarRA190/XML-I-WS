import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneConversationComponent } from './one-conversation.component';

describe('OneConversationComponent', () => {
  let component: OneConversationComponent;
  let fixture: ComponentFixture<OneConversationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneConversationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
