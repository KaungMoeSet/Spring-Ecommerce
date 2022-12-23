import { Injectable } from '@angular/core';
import {ADMIN_API_URL} from "../Config/Config";
import {Product} from "../model/product";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

const ADMIN_API = ADMIN_API_URL + "product";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  _productArr: Product[] = [];
  _products = new BehaviorSubject<Product[]>([]);
  readonly products = this._products.asObservable();
  constructor( private http: HttpClient) {

    this.fetchAllProducts(0, 10).subscribe(products => {
        this._productArr = products;
        this.emitChange();
    })
  }

  private emitChange() {
    this._products.next(this._productArr);
  }

  fetchAllProducts(pageNo: number, size: number): Observable<Product[]> {
    return this.http.get<Product[]>(ADMIN_API + "/productPaging",
                             { params: {
                                        pageNo: pageNo,
                                        size: size
                                      }});
  }
}
