import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../../model/product";

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})
export class ProductTableComponent implements OnInit {

  @Input() products: Product[] =[];
  @Output() editProductEvent = new EventEmitter<any>();
  @Output() deleteProductEvent = new EventEmitter<any>();
  editmode: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  editProduct(index: number) {
    this.editProductEvent.emit(
      this.products[index]
    );
  }

  detailProduct(id: string) {

  }

  deleteProduct(index: number) {
    this.deleteProductEvent.emit(
      this.products[index]
    );
  }
}
