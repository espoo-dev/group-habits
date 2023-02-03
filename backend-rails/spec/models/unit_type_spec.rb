# == Schema Information
#
# Table name: unit_types
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_unit_types_on_name  (name) UNIQUE
#
require 'rails_helper'

RSpec.describe UnitType, type: :model do
  context 'validations' do
    subject { create(:unit_type) }

    it { should validate_presence_of(:name) }

    it { should validate_uniqueness_of(:name) }
  end
end
