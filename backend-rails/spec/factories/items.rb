# == Schema Information
#
# Table name: categories
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  user_id    :integer          not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_categories_on_user_id           (user_id)
#  index_categories_on_user_id_and_name  (user_id,name) UNIQUE
#
FactoryBot.define do
  factory :item do
    name { 'laptop' }
    extra_info { 'nice laptop' }
    sale_price { 2000.00 }
    purchase_price { 1500.55 }
    sales_unit { 'unit' }
    item_type { 'product' }

    association :user
    association :category
  end
end
