type Color = {
  color: string;
}

export class Product {
  id?: string;
  name: string;
  price: number;
  date: Date;
  image: string;
  category: string;
  color: Color[];
  status: string;
  quantity: number;
  description: string;

  constructor(id: string, name: string, price: number, date: Date, image: string, category: string, color: Color[], status: string, quantity: number, description: string) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
    this.image = image;
    this.category = category;
    this.color = color;
    this.status = status;
    this.quantity = quantity;
    this.description = description;
  }
}
