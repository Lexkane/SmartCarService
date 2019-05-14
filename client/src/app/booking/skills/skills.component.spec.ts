import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StoSkillComponent } from './skills.component';

describe('SkillsComponent', () => {
  let component: StoSkillComponent;
  let fixture: ComponentFixture<StoSkillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StoSkillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StoSkillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
