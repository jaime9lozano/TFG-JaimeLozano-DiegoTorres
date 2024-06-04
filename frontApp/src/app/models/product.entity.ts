import { Category } from './category.entity'
import { Evaluation } from './evaluation.entity'

export interface Product {
  id?: number
  name: string
  price: number
  stock: number
  gluten: boolean
  image: Uint8Array
  imageExtension: string
  createdAt: Date
  updatedAt: Date
  deletedAt: Date | null
  category: Category
  averageRating: number
  priceOffer?: number
  // evaluations: Evaluation[]
}

export interface ProductSaveDto {
  name: string
  price: number
  stock: number
  gluten: boolean
  categoryId: number
}
