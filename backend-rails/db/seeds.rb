if Rails.env.development?
  AdminUser.create!(email: 'admin@example.com', password: 'password')
  group = Group.create!(name: 'Espoo lovers')
  Habit.create!(name: 'Drink water', group:)
  Habit.create!(name: 'Exercise', group:)
  Habit.create!(name: 'Eat 2 fruits', group:)
  user = User.create!(email: 'user@email.com', password: '123456789', group:)
  category = Category.create!(name: 'Home', user:)
  Item.create!(name: 'product 1', extra_info: 'info 1', sale_price: 11, purchase_price: 5,
               item_type: 'product', user:, category:)
  Item.create!(name: 'product 2', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
               item_type: 'product', user:, category:)
  Item.create!(name: 'service 1', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
               item_type: 'service', user:, category:)
  Item.create!(name: 'service 2', extra_info: 'info 2', sale_price: 11, purchase_price: 5,
               item_type: 'service', user:, category:)

  Customer.create!(name: 'John', document_number: '00321333355', customer_type: Customer.customer_types[:person],
                   phone: '85996686868', user:)
  Customer.create!(name: 'company', document_number: '123456789321654',
                   customer_type: Customer.customer_types[:business], phone: '25996686868', user:)
  Setting.create_or_find_by!(key: 'min_version', value: '0.0')
end
