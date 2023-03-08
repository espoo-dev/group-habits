# == Schema Information
#
# Table name: sales_units
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_sales_units_on_name  (name) UNIQUE
#
class SalesUnit < ApplicationRecord
  include NameFilterable

  has_many :items, dependent: :destroy

  validates :name, presence: true
  validates :name, uniqueness: true
end
