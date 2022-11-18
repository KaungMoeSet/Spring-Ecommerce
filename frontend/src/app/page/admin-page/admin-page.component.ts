import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  @ViewChild('template') template: any = null;
  modalRef?: BsModalRef;
  products: Array<string> = [];
  loading = false;
  submitted = false;
  minDate: Date;
  maxDate: Date;

  constructor(private formBuilder: FormBuilder,
              private modalService: BsModalService,) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 20, 0, 1);
    console.log(this.minDate);
    this.maxDate = new Date(currentYear + 1, 11, 31);
    console.log(this.maxDate);
  }

  productForm = this.formBuilder.group({
    productName: ['', [Validators.required, Validators.maxLength(50)]],
    price: [0, Validators.required],
    date: [Date, Validators.required],
    category: ['', Validators.required],
    colors: this.formBuilder.group({
      black: [false, Validators.required],
      white: [false, Validators.required],
      gray: [false, Validators.required],
      blue: [false, Validators.required],
      green: [false, Validators.required],
      purple: [false, Validators.required],
      violet: [false, Validators.required],
      yellow: [false, Validators.required],
    }),
    status: ['', Validators.required],
    quantity: [0, Validators.required],
    description: ['', Validators.required],
    image: ['', Validators.required],
  });

  Categories = [
    "Mobile",
    "Laptop",
    "Earphone",
    "Headphone",
    "Smartwatch"
  ];

  Status = [
    "in_stock",
    "out_of_stock",
    "coming_soon"
  ]

  ngOnInit(): void {
  }

  // onFileChange(event ?: Event) {
  //   // @ts-ignore
  //   if (event.target.files.length > 0) {
  //     const file = event?.target;
  //     console.log(file);
  //   }
  // }

  quantityKeyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;

    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  priceKeyPress(event: any) {
    const pattern = /^\d*\.?\d*$/;

    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  closeModal() {
    this.modalRef?.hide();
  }

  newProductClick() {
    this.openModal(this.template);
  }

  saveOrUpdateProduct() {
    console.log("Form data: ", this.productForm.value);
    console.log("Color data", this.productForm.value.colors);
  }

  get productFormControl() {
    return this.productForm.controls;
  }
}
