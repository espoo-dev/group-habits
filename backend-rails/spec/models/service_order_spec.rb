# == Schema Information
#
# Table name: service_orders
#
#  id              :bigint           not null, primary key
#  name            :string           not null
#  extra_info      :string
#  status          :string           not null
#  creation_date   :datetime
#  conclusion_date :datetime
#  discount        :decimal(8, 2)
#  user_id         :integer          not null
#  customer_id     :integer          not null
#  created_at      :datetime         not null
#  updated_at      :datetime         not null
#
# Indexes
#
#  index_service_orders_on_customer_id       (customer_id)
#  index_service_orders_on_user_id           (user_id)
#  index_service_orders_on_user_id_and_name  (user_id,name) UNIQUE
#
require 'rails_helper'

RSpec.describe ServiceOrder, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
    it { should belong_to(:customer).required }
  end

  context 'validations' do
    context 'presence' do
      it { should validate_presence_of(:name) }
    end
  end

  context 'uniqueness' do
    subject { create(:service_order) }
    it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
  end

  context 'inclusion' do
    it { should validate_inclusion_of(:status).in_array(ServiceOrder::STATUSES) }
  end
end
