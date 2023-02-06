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
require 'rails_helper'

RSpec.describe SalesUnit, type: :model do
  context 'validations' do
    subject { create(:sales_unit) }

    it { should validate_presence_of(:name) }

    it { should validate_uniqueness_of(:name) }
  end

  context 'relationships' do
    it { should have_many(:items).dependent(:destroy) }
  end
end
