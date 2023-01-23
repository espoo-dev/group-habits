# == Schema Information
#
# Table name: items
#
#  id             :bigint           not null, primary key
#  name           :string           not null
#  extra_info     :string
#  sale_price     :decimal(8, 2)    not null
#  purchase_price :decimal(8, 2)
#  sales_unit     :string           not null
#  item_type      :string           not null
#  category_id    :string           not null
#  user_id        :string           not null
#  created_at     :datetime         not null
#  updated_at     :datetime         not null
#
# Indexes
#
#  index_items_on_category_id       (category_id)
#  index_items_on_user_id           (user_id)
#  index_items_on_user_id_and_name  (user_id,name) UNIQUE
#
class Item < ApplicationRecord
  ITEM_TYPES = %w[product service].freeze

  belongs_to :user
  belongs_to :category

  validates :name, presence: true
  validates :name, uniqueness: { scope: :user_id, case_sensitive: false }
  validates :sale_price, presence: true
  validates :sales_unit, presence: true
  validates :item_type, presence: true
  validates :item_type, inclusion: ITEM_TYPES

  scope :by_name_like, lambda { |name_like|
    where('name LIKE ?', "%#{name_like}%").order(:id)
  }
  scope :by_item_type, lambda { |item_type|
                         return where(item_type:) if item_type.present?

                         all
                       }
end
