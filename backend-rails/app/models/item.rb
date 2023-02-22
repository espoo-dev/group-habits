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
#  category_id    :integer
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
class Item < ApplicationRecord
  include NameFilterable

  ITEM_TYPES = %w[product service].freeze

  belongs_to :user
  belongs_to :sales_unit, optional: true
  belongs_to :category, optional: true
  has_many :item_service_orders, dependent: :destroy
  has_many :service_orders, through: :item_service_orders, dependent: :destroy

  validates :name, presence: true
  validates :name, uniqueness: { scope: :user_id, case_sensitive: false }
  validates :sale_price, presence: true
  validates :item_type, presence: true
  validates :category, presence: true, if: -> { item_type == 'product' }
  validates :item_type, inclusion: ITEM_TYPES

  scope :by_item_type, lambda { |item_type|
                         return where(item_type:) if item_type.present?

                         all
                       }
end
