if Rails.env.development?
  AdminUser.create!(email: 'admin@example.com', password: 'password')
  group = Group.create!(name: 'Espoo lovers')
  Habit.create!(name: 'Drink water', group:)
  Habit.create!(name: 'Exercise', group:)
  Habit.create!(name: 'Eat 2 fruits', group:)
  user = User.create!(email: 'user@email.com', password: '123456789', group:)
  category = Category.create!(name: 'Home', user:)
  sales_unit = SalesUnit.create!(name: 'unit')
  sales_unit2 = SalesUnit.create!(name: 'meter')
  item1 = Item.create!(name: 'product 1', extra_info: 'info 1', sale_price: 11, purchase_price: 5,
                       item_type: 'product', user:, category:, sales_unit:)
  item2 = Item.create!(name: 'product 2', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
                       item_type: 'product', user:, category:, sales_unit: sales_unit2)
  Item.create!(name: 'service 1', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
               item_type: 'service', user:)
  service = Item.create!(name: 'service 2', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
                         item_type: 'service', user:)

  customer = Customer.create!(name: 'John', document_number: '00321333355',
                              customer_type: Customer.customer_types[:person], phone: '85996686868', user:)
  Customer.create!(name: 'company', document_number: '123456789321654',
                   customer_type: Customer.customer_types[:business], phone: '25996686868', user:)

  service_order = ServiceOrder.create!(status: ServiceOrder::STATUSES.first, user:, customer:)

  ItemServiceOrder.create!(item_id: item1.id, service_order_id: service_order.id)
  ItemServiceOrder.create!(item_id: item2.id, service_order_id: service_order.id)
  ItemServiceOrder.create!(item_id: service.id, service_order_id: service_order.id)

  Setting.create_or_find_by!(key: 'min_version', value: '0.0')
end
