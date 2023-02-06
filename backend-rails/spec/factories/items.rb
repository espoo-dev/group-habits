# == Schema Information
#
# Table name: items
#
#  id             :bigint           not null, primary key
#  name           :string           not null
#  extra_info     :string
#  sale_price     :decimal(8, 2)    not null
#  purchase_price :decimal(8, 2)
#  item_type      :string           not null
#  category_id    :string           not null
#  user_id        :string           not null
#  created_at     :datetime         not null
#  updated_at     :datetime         not null
#  sales_unit_id  :integer
#
# Indexes
#
#  index_items_on_category_id       (category_id)
#  index_items_on_sales_unit_id     (sales_unit_id)
#  index_items_on_user_id           (user_id)
#  index_items_on_user_id_and_name  (user_id,name) UNIQUE
#
FactoryBot.define do
  factory :item do
    name { 'laptop' }
    extra_info { 'nice laptop' }
    sale_price { 2000.00 }
    purchase_price { 1500.55 }
    item_type { 'product' }

    association :user
    association :category
    association :sales_unit
  end
end
