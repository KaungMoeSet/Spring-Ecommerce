type Color = {
  color: string;
}

type ProductDetail = {
  id: string;
  date: Date;
  image: string;
  color: Color[];
  description: string;
}
export class Product {
  id?: string;
  name: string;
  category: string;
  status: string;
  quantity: number;
  price: number;
  details?: ProductDetail;

  constructor(id: string, name: string, category: string, status: string, quantity: number, price: number) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.status = status;
    this.quantity = quantity;
    this.price = price;
  }
}
